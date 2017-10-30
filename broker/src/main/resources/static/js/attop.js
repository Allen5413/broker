function callApp(funcId,msg){
	try{
		window.app.callFun(funcId,msg);
	}catch(e){
		//alert(e);
	}
}
function hideButtom(){
	callApp(103,"");
}

