(function(w,d,u){
	var loginForm = util.get('loginForm');
	if(!loginForm){
		return;
	}
	var userName = loginForm['userName'];
	var password = loginForm['password'];
	var isSubmiting = false;
	var loading = new Loading();
	
	var getcurPath =function() {
		var curWwwPath=window.document.location.href;   
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
		var pathName=window.document.location.pathname;  
		var pos=curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8083  
		var localhostPaht=curWwwPath.substring(0,pos); //获取带"/"的项目名，如：/uimcardprj  
		var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);   
	  	var rootPath = localhostPaht+projectName;  
        return rootPath;
    };
	
	var page = {
		init:function(){
			loginForm.addEventListener('submit',function(e){
				if(!isSubmiting && this.check()){
					var value1 = userName.value;
					var value2 = md5(password.value);
					isSubmiting = true;
					loading.show();

					ajax({
						data:{userName:value1,password:value2},
						url:getcurPath()+'/postlogin',
						success:function(result){
							loading.hide();
							location.href = getcurPath()+'/';
						},
						error:function(message){
							loading.result(message||'登录失败');
							isSubmiting = false;
						}
					});
				}
			}.bind(this),false);
			[userName,password].forEach(function(item){
				item.addEventListener('input',function(e){
					item.classList.remove('z-err');
				}.bind(this),false);
			}.bind(this));
		},
		check:function(){
			var result = true;
			[
				[userName,function(value){return value == ''}],
				[password,function(value){return value == ''}]
			].forEach(function(item){
				var value = item[0].value.trim();
				if(item[1](value)){
					item[0].classList.add('z-err');
					result = false;
				}
				item[0].value = value;
			});
			return result;
		}
	};
	page.init();
})(window,document);