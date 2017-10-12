package com.allen.util;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
/**
 * Created by Allen on 2017/10/12.
 */
public class AttopUtil {

    public static void main(String[] args) {
        String shopId="1001";
        String secret="BL4vOSGzQ3iE7ah1umNdvAV8escNQPlzkTP1NcTuhWBg4WObrXFPew5e28lXafjot2SQniZ4WbDIBuFSgNz9bqUoxZ44EUxT7hWbcdZCeIrTowhN3nBCdHeO1DFMFtlg";
        String portUrl="http://ttt.attop.com/dangport/attop.do";

        JSONObject json = getPortMsg(201,"zz=100001&msg="+toUrlEncode("千"), shopId, secret, portUrl);
        System.out.println(json);
    }


    public static JSONObject getPortMsg(int funcId,String query,String shopId,String secret,String portUrl){
        String result="";
        try{
            SortedMap<String,Object> p=new TreeMap<String,Object>();
            p.put("shopId", shopId);
            p.put("funcId", funcId);
            if(!(query==null||"".equals(query.trim()))){
                String[] a=query.split("\\&");
                for(String b:a){
                    String[] c=b.split("\\=",2);
                    p.put(c[0],toUrlDecode(c[1]));
                }
            }
            String mac=createMac(p,secret);
            String url=portUrl+"?funcId="+funcId+"&shopId="+shopId;
            if(!(query==null||"".equals(query.trim()))){
                url+="&"+query+"&mac="+mac;
            }else{
                url+="&mac="+mac;
            }
            System.out.println(url);
            result=getContent(url);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }
    public static String getContent(String url) {
        String result ="";
        try {
            URL ml = new URL(url);
            URLConnection mcl = ml.openConnection();
            DataInputStream is=new DataInputStream(mcl.getInputStream());
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            byte[] bf=new byte[128];
            int len=0;
            while ( (len=is.read(bf))!= -1) {
                tmp.write(bf,0,len);
            }
            result = new String(tmp.toByteArray(),"utf-8");
            is.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;

    }
    public static String createMac(SortedMap<String,Object> parameters,String secret){
        String result="";
        try{
            StringBuffer sb = new StringBuffer();
            Set es = parameters.entrySet();
            Iterator it = es.iterator();
            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                String k = (String)entry.getKey();
                Object v = entry.getValue();
                if(null != v && !"".equals(v)  && !"mac".equals(k)) {
                    sb.append(k + "=" + v + "&");
                }
            }
            sb.append("&secret=" + secret);
            result=getMd5(sb.toString());
        }catch(Throwable e){
            e.printStackTrace();
        }
        return result;
    }

    public static String getMd5(String text) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("UTF-8"));
            byte[] s = md.digest();
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
                        .substring(6);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result;
    }
    public static String toUrlEncode(String str){
        String result=str;
        try{
            result = URLEncoder.encode(str, "utf-8");
        }catch(Throwable e){
            e.printStackTrace();
        }
        return result;
    }
    public static String toUrlDecode(String str){
        String result=str;
        try{
            result = URLDecoder.decode(str, "utf-8");
        }catch(Throwable e){
            e.printStackTrace();
        }
        return result;
    }
}
