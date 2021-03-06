package com.allen.util;

import com.allen.base.exception.BusinessException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Allen on 2015/6/22.
 */
public class UpLoadFileUtil {

    /**
     * 上传图片
     * @param request
     * @param imgType
     * @param imgSize
     * @param imgMaxCount
     * @param imgPath
     * @return
     * @throws Exception
     */
    public static String uploadImg(HttpServletRequest request,List<MultipartFile> fileList,String imgType, int imgSize, int imgMaxCount, String imgPath, String saveFileName)throws Exception{
        String imgUrl = "";
        //创建一个通用的多部分解析器
        //CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        //if(commonsMultipartResolver.isMultipart(request)) {
            //取得request中的所有文件名
            if(null != fileList && 0 < fileList.size()) {
                //判断上传文件数量
                if (fileList.size() > imgMaxCount) {
                    throw new BusinessException("最多只能上传" + imgMaxCount + "张图片！");
                }
                for (int i=0; i<fileList.size(); i++) {
                    MultipartFile file = fileList.get(i);
                    //取得上传文件
                    if (file != null) {
                        //取得当前上传文件的文件名称
                        String fileName = file.getOriginalFilename();
                        //取得当前上传文件大小
                        long fileSize = file.getSize();
                        //如果名称不为"",说明该文件存在，否则说明该文件不存在
                        if (!StringUtil.isEmpty(fileName.trim()) && 0 < fileSize) {
                            //判断上传文件类型
                            String fileExtention = StringUtil.substringAfterLast(fileName, ".").toLowerCase();
                            if (!fileExtention.matches(imgType)) {
                                throw new BusinessException("图片的类型只能是：" + imgType + "！");
                            }
                            //判断上传文件大小
                            if (fileSize / 1024 > imgSize) {
                                throw new BusinessException("图片最大不能超过" + imgSize/1024 + "MB");
                            }
                            //重命名上传后的文件名
                            fileName = StringUtil.isEmpty(saveFileName) ? UUID.randomUUID().toString() : saveFileName + ".png";
                            //判断路径是否存在，不存在就创建
                            //定义上传路径
                            String savePath = imgPath + fileName;
                            //服务器tomcat路径+保存路径
                            File localFile = new File(request.getRealPath("") + imgPath);
                            localFile.mkdirs();
                            localFile = new File(request.getRealPath("") + savePath);
                            file.transferTo(localFile);
                            imgUrl += savePath + ",";
                        }
                    }
                }
            }
        //}
        return  imgUrl.substring(0, imgUrl.length() > 0 ? imgUrl.length()-1 : 0);
    }

    /**
     * 删除文件
     * @param path
     * @throws Exception
     */
    public static void delFile(HttpServletRequest request, String path)throws Exception{
        File file = new File(request.getRealPath("") + path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static boolean delDir(String path)throws Exception{
        File dir = new File(path);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                File childrenFile = new File(dir, children[i]);
                boolean success = delDir(childrenFile.getPath());
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(HttpServletRequest request, String oldPath, String newPath, String fileName) {
        try {
            int bytesum = 0;
            int byteread = 0;
            oldPath = request.getRealPath("")+oldPath;
            newPath = request.getRealPath("")+newPath;
            new File(newPath).mkdirs();
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath+"/"+fileName);
                byte[] buffer = new byte[2048];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 剪切文件
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void custFile(HttpServletRequest request, String oldPath, String newPath, String fileName) {
        try {
            copyFile(request, oldPath, newPath, fileName);
            delFile(request, oldPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 剪切并压缩文件
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void custAndThumbnailsFile(HttpServletRequest request, String oldPath, String newPath, String smallImgPath, String fileName) {
        try {
            String oldPath2 = request.getRealPath("")+oldPath;
            String newPath2 = request.getRealPath("")+newPath+fileName;
            String smallImgPath2 = request.getRealPath("")+smallImgPath+fileName;
            File input = new File(oldPath2);
            Thumbnails.Builder<File> fileBuilder = Thumbnails.of(input).scale(1.0).outputQuality(1.0);
            BufferedImage src = fileBuilder.asBufferedImage();
            int width = src.getWidth();
            int height = src.getHeight();
            //生成大图
            if(width <= 1280 && height <= 1280){
                copyFile(request, oldPath, newPath, fileName);
            }else {
                int newWidth = 1280;
                int newHeight = 1280;
                if(width > height){
                    double temp = width / 1280;
                    newHeight = (int) (height / temp);
                }else{
                    double temp = height / 1280;
                    newWidth = (int) (width / temp);
                }
                //按比例缩放
                Thumbnails.of(oldPath2).size(newWidth, newHeight).toFile(newPath2);
                compressPic(newPath2, newPath2, 500, 0.98f, 0);
            }
            //生成缩略图
            int newWidth = 200;
            int newHeight = 200;
            if(width >= height){
                if(height >= 200){
                    double temp = height / 200;
                    newWidth = (int)(width / temp);
                }else{
                    double temp = 200 / height;
                    newWidth = (int)(width * temp);
                }
            }else{
                if(width >= 200){
                    double temp = width / 200;
                    newHeight = (int)(height / temp);
                }else{
                    double temp = 200 / width;
                    newHeight = (int)(height * temp);
                }
            }
            //生成缩略图固定尺寸，不按比例
            Thumbnails.of(oldPath2).size(newWidth, newHeight).keepAspectRatio(false).toFile(smallImgPath2);
            Thumbnails.of(smallImgPath2).sourceRegion(Positions.CENTER,200,200).size(200, 200).toFile(smallImgPath2);
            compressPic(smallImgPath2, smallImgPath2, 50, 0.98f, 0);
            delFile(request, oldPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 剪切并压缩文件
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void custAndThumbnailsFile(HttpServletRequest request, String oldPath, String newPath, String fileName) {
        try {
            //生成形象照
            File input = new File(request.getRealPath("")+oldPath);
            Thumbnails.Builder<File> fileBuilder = Thumbnails.of(input).scale(1.0).outputQuality(1.0);
            BufferedImage src = fileBuilder.asBufferedImage();
            int width = src.getWidth();
            int height = src.getHeight();
            int newWidth = 500;
            int newHeight = 500;
            if(width >= height){
                if(height >= 500){
                    double temp = height / 500;
                    newWidth = (int)(width / temp);
                }else{
                    double temp = 500 / height;
                    newWidth = (int)(width * temp);
                }
            }else{
                if(width >= 500){
                    double temp = width / 500;
                    newHeight = (int)(height / temp);
                }else{
                    double temp = 500 / width;
                    newHeight = (int)(height * temp);
                }
            }
            String oldPath2 = request.getRealPath("")+oldPath;
            String newPath2 = request.getRealPath("")+newPath+fileName;
            //按比例缩放
            Thumbnails.of(oldPath2).size(newWidth, newHeight).keepAspectRatio(false).toFile(newPath2);
            Thumbnails.of(newPath2).sourceRegion(Positions.CENTER,500,500).size(500, 500).toFile(newPath2);
            compressPic(newPath2, newPath2, 100, 0.95f, 0);
            delFile(request, oldPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把图片压缩到指定内存大小内
     * @param desPath
     * @param desFileSize
     * @param accuracy
     * @throws IOException
     */
    private static void commpressPicCycle(String desPath, long desFileSize, double accuracy) throws IOException {
        File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = srcFileJPG.length();
        // 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return;
        }
        // 计算宽高  
        BufferedImage bim = ImageIO.read(srcFileJPG);
        int srcWdith = bim.getWidth();
        int srcHeigth = bim.getHeight();
        int desWidth = new BigDecimal(srcWdith).multiply(new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeigth).multiply(new BigDecimal(accuracy)).intValue();
        Thumbnails.of(desPath).size(desWidth, desHeight).outputQuality(accuracy).toFile(desPath);
        commpressPicCycle(desPath, desFileSize, accuracy);
    }

    /**
     * 降低图片品质，保持图片尺寸不变
     * @param srcFilePath
     * @param descFilePath
     * @return
     */
    private static void compressPic(String srcFilePath, String descFilePath, long desFileSize, float accuracy, int maxNum)throws Exception{
        File srcFileJPG = new File(srcFilePath);
        long srcFileSizeJPG = srcFileJPG.length();
        // 2、判断大小，如果小于desFileSize kb，不压缩；如果大于等于 desFileSize kb，压缩; 如果递归5次还不能满足大小要求，强制跳出
        if (srcFileSizeJPG <= desFileSize * 1024 || 5 < maxNum) {
            return;
        }
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality(accuracy);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        if(StringUtil.isEmpty(srcFilePath)) {
            return;
        } else {
            file = new File(srcFilePath);
            src = ImageIO.read(file);
            out = new FileOutputStream(descFilePath);
            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            out.flush();
            out.close();
            maxNum++;
            compressPic(srcFilePath, descFilePath, desFileSize, accuracy, maxNum);
        }
    }
}
