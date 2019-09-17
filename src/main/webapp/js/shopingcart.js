$(function() {
	$.ajax({
		url : "http://localhost:8083/DubboShopingCart/queryShopingCart.action",
		type : "POST",
		data : {},
		dataType : "json",
		success : function(successReturnValue) {
			var shopingcart = $("#shopingcart");
			if(successReturnValue==null){
				$("#shopingcart").html("");
				$("<h1>购物车内无商品</h1>").appendTo(shopingcart);	
				
			}else{
				$("<button class = 'btn' type = 'button' onclick='del()'>删除</button>").appendTo(shopingcart);
				$("<button class = 'btn' type = 'button' onclick='buy()'>购买</button>").appendTo(shopingcart);
				$.each(successReturnValue, function(i, v) {
					$("<tr id=tr"+i+"></tr>").appendTo(shopingcart);
					var shopingcartTr = $("#shopingcart #tr"+i+"");
					$("<td><input name='itemId' type='checkbox' value="+v.item.id+"></td>").appendTo(shopingcartTr);
					$("<td><img src="+v.picture.src+"></td>").appendTo(shopingcartTr);
					$("<td><p>"+v.item.itemName+"</p></td>").appendTo(shopingcartTr);
					$("<td><button class = '.btn-xs glyphicon glyphicon-plus ' type='button' onclick='addItem("+v.item.id+")'></button><p>"+v.number+"</p><button class = '.btn-xs glyphicon glyphicon-minus' type='button' onclick='reduceItem("+v.item.id+")'></button></td>").appendTo(shopingcartTr);
					$("<td><p>"+v.item.price+"</p></td>").appendTo(shopingcartTr);
					$("<td><p>"+v.item.price*v.number+"</p></td>").appendTo(shopingcartTr);
					
				});
			}
		},
		error:function(errorReturnValue){
			//window.location.href ="Http://localhost:8081/DubboLogin";
			alert(errorReturnValue);
		}
	});
});
function del(){
	$("form").attr("action","../../deleteItemInShopingCart.action");
	$("form").submit();
}
function buy(){
	$("form").attr("action","http://localhost:8083/DubboShopingCart/buyChosenItemInShopingCart.action");
	$("form").submit();
}
function addItem(itemId){
	alert("addItem");
	alert(itemId);
}
function reduceItem(itemId){
	alert("reduceItem");
	alert(itemId);
}