//addLoadEvent 函数
function addLoadEvent(func){
	var oldonload = window.onload;
	if(typeof window.onload != "function"){
		window.onload = func;
	} else {
		window.onload = function(){
			oldonload();
			func();
		}
	}
}

//insertAfter函数
function insertAfter(newElement,targetElement){
	var parent=targetElement.parentNode;
	if (parent.lastChild == targetElement){
		parent.appendChild(newElement);
	} else {
		parent.insertBefore(newElement,tragetElement.nextSibling);
	}
}

//addClass 函数
function addClass(element,value){
	if(!element.className){
		element.className = value;
	} else {
		newClassName = element.className;
		newClassName+= "";
		newClassName+= value;
		element.className = newClassName;
	}
}


 $(function(){
       $(".tabs-title").on("click","li",function(){
         // 设index为当前点击
         var index = $(this).index();
         // 点击添加样式利用siblings清除其他兄弟节点样式
         $(this).addClass("on").siblings().removeClass("on");
         // 同理显示与隐藏
         $("#bdmod").find(".bd-mod-view").eq(index).show().siblings().hide();
       })
})

var modal = document.getElementById("show-select");
var btn = document.getElementById("sift-ar");
btn.onclick = function(){
	modal.style.display = "block";
}
modal.onclick = function(){
	modal.style.display = "none";
}





