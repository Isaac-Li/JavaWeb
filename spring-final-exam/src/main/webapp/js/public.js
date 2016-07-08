(function(w,d,u){
	var form = util.get('form');
	if(!form){
		return;
	}
	var title = form['title'];
	var summary = form['summary'];
	var image = form['image'];
	var detail = form['detail'];
	var price = form['price'];
	var pic = form['pic'];
	var uploadInput = form['file'];
	var isSubmiting = false;
	var imgpre = util.get('imgpre');
	var loading = new Loading();
	var imageUrl;
	var imageMode = "urlUpload";
	
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
			var $ = function(id){
				return document.getElementById(id);
			};
			
			window.onload=function(){
				 if(pic.value=='file'){
					 var s,h;
						s='fileUpload';
						imageMode = 'fileUpload';
						image.classList.remove("z-err");
						uploadInput.classList.remove("z-err");
						$('fileUpload').style.display='block';
						$('urlUpload').style.display='none';
				 }
			}
			
			$('uploadType').onclick = function(e){
				e = window.event || e;
				o = e.srcElement || e.target;
				if(o.nodeName==="INPUT"){
					var s,h;
					o.value==='url'?(s='urlUpload',h='fileUpload'):(s='fileUpload',h='urlUpload');
					imageMode = o.value==='url'?"urlUpload":"fileUpload";
					image.classList.remove("z-err");
					uploadInput.classList.remove("z-err");
					$(s).style.display='block';
					$(h).style.display='none';
				}
			};
			

			
			$('upload').addEventListener('click', function (){
				
				uploadInput.addEventListener('change', function() {
					console.log(uploadInput.files) // File listing!
				});
				for (var i = 0, fileCount = uploadInput.files.length; i < fileCount; i++) {
					 console.log(uploadInput.files[i]);
				}
				var maxAllowedSize = 1000000;
				var file = uploadInput.files[0];

					if(uploadInput.files[0].size > maxAllowedSize) {
						alert("超过文件上传大小限制");
					}else{
		            var form = new FormData();
		            form.append('file', file, file.name);
		            form.enctype = "multipart/form-data";

		            var xhr = new XMLHttpRequest();
		            xhr.open("post", getcurPath()+"/upload", true);
		            xhr.onload = function () {
		            	  if (xhr.status === 200) {
		            	    alert("文件上传成功");
		            	    var o = JSON.parse(xhr.responseText);
		            	    imageUrl = o && o.result;		            	    
		            	    image.value = imageUrl;
		            	    imgpre.src = imageUrl;
		            	  } else {
		            	    alert('An error occurred!');
		            	  }
		            	};
		            xhr.send(form);
				}
			});
			form.addEventListener('submit',function(e){
				if(!isSubmiting && this.check()){
					price.value = Number(price.value);
					isSubmiting = true;
					form.submit();
				}
			}.bind(this),false); 
			[title,summary,image,detail,price].forEach(function(item){
				item.addEventListener('input',function(e){
					item.classList.remove('z-err');
				}.bind(this),false);
			}.bind(this));
			image.addEventListener('input',function(e){
				var value = image.value.trim();
				if(value != ''){
					imgpre.src = value;
				}
			}.bind(this),false);
		},
		check:function(){
			var result = true;
			[
				[title,function(value){return value.length<2 || value.length>80}],
				[summary,function(value){return value.length<2 || value.length>140}],
				[image,function(value){return imageMode == "urlUpload" && value == ''}],
				[detail,function(value){return value.length<2 || value.length>1000}],
				[price,function(value){return value == '' || !Number(value)}]
			].forEach(function(item){
				var value = item[0].value.trim();
				if(item[1](value)){
					item[0].classList.add('z-err');
					result = false;
				}
				item[0].value = value;
			});
			if(imageMode == "fileUpload" && !imageUrl){
				uploadInput.classList.add('z-err');
				result = false;
			}
			return result;
		}
	};
	page.init();
})(window,document);