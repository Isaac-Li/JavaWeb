(function(w,d,u){
	var settleAccount = util.get('settleAccount');
	if(!settleAccount){
		return;
	}
	var name = 'products';
	var products = util.getCookie(name);
	var $ = function(id){
		return document.getElementById(id);
	}
	
	var str = "<tr>" + 
			  "<th>" + '内容名称'  + "</th>"+ 
			  "<th>" + '数量' + "</th>" +
			  "<th>" + '价格' + "</th>" +
			  "</tr>";

	if(products !=null){
		for(var i = 0; i < products.length; i++){
			str = str + 
			"<tr>" + 
			"<td>" + products[i].title  + "</td>"+
			"<td>" + 
			"<span id=\"plusNum\" class=\"lessNum\"> <a>"+ "-" + "</a> </span>" +
			"<span class=\"totalNum\" id=\"allNum\">" + products[i].num + "</span>" +
			"<span id=\"thisId\">" + products[i].id + "</span>" +
			"<span id=\"addNum\" class=\"moreNum\"> <a>"+ "+" + "</a> </span>" + "</td>" +
			"<td>" + products[i].price + "</td>" +
			"</tr>";
		}

	}
	
	$("newTable").innerHTML = str;
	
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

	
	window.onload = function(){

	};

	var loading = new Loading();
	var layer = new Layer();
	$('Account').onclick = function(e){
		var newProducts = products.map(function(arr){
			return {'id':arr.id,'number':arr.num};
		});
		console.log(newProducts);
		var ele = e.target;
			layer.reset({
				content:'确认购买吗？',
				onconfirm:function(){
					layer.hide();
					loading.show();
					
					var xhr = new XMLHttpRequest();
					var data = JSON.stringify(newProducts);
					xhr.onreadystatechange = function(){
						 if(xhr.readyState == 4){
				                var status = xhr.status;
				                if(status >= 200 && status < 300 || status == 304){
				                	var json = JSON.parse(xhr.responseText);
				                	if(json && json.code == 200){
				                		loading.result('购买成功',function(){location.href = './account';});
				                		util.deleteCookie(name);
				                	}else{
				                		alert(json.message);
				                	}
				                }else{
				                	loading.result(message||'购买失败');
				                }
				            }
					};
					 xhr.open('post',getcurPath()+'/buy');
					 xhr.setRequestHeader('Content-Type','application/json');
					 xhr.send(data);
				}.bind(this)
			}).show();
			return;
	};
	
	$('back').onclick = function(){
		location.href = window.history.back();
	}
	
	$('plusNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		if(num > 1){
			num --;
			$('allNum').innerHTML = num;
		}else{
			alert("购买的商品数量不能为零！");
		}
	};

	$('addNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		num ++;
		$('allNum').innerHTML = num;
	};
})(window,document);