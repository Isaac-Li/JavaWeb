(function(w,d,u){
	var plist = util.get('plist');
	if(!plist){
		return;
	}
	var layer = new Layer();
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
			plist.addEventListener('click',function(e){
				var ele = e.target;
				var delId = ele.dataset && ele.dataset.del;
				if(delId){
					this.ondel(delId);
					return;
				}
			}.bind(this),false);
		},
		ondel:function(id){
			layer.reset({
				content:'确定要删除该内容吗？',
				onconfirm:function(){
					layer.hide();
					loading.show();
					ajax({
						url:getcurPath()+'/delete',
						data:{id:id},
						success:function(json){
							this.delItemNode(id);
							loading.result('删除成功');
						}.bind(this)
					});
				}.bind(this)
			}).show();
		},
		delItemNode:function(id){
			var item = util.get('p-'+id);
			if(item && item.parentNode){
				item.parentNode.removeChild(item);
			}
		}
	};
	page.init();
})(window,document);