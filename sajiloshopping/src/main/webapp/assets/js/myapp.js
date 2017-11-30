$(function(){
	//To solve the active menu focus problem
	switch(menu){	
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact Us':
			$('#contact').addClass('active');
			break;
		case 'All Products':
			$('#listProducts').addClass('active');
			break;
		default:
			if(menu == "Home") break;
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
});


//code for jquery datatable

var $table = $('#productListTable');

//execute only where we have this table
if($table.length){
//	console.log("i am inside table!");
	
	var jsonUrl = '';
	if(window.categoryId == ''){
		jsonUrl = window.contextRoot + "/json/data/all/products";
	}
	else{
		jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
	}
	
	$table.DataTable({
		lengthMenu: [[3,5,10,-1],['3','5','10','All']],
		pageLength: 5,
		ajax:{
			url: jsonUrl,
			dataSrc: '' //collection of objects without any name :)
		},
		columns:[
			{
				data: 'code',
				mRender: function(data, type, row){
					return '<img src="'+ window.contextRoot +'/resources/images/' +data+ '.jpg" class="dataTableImg"/>';
				}
			},
			{
				data: 'name'
			},
			{
				data: 'brand'
			},
			{
				data: 'unitPrice',
				mRender: function(data,type,row){
					return 'Rs. ' + data;
				}
			},
			{
				data: 'quantity',
				mRender: function(data,type,row){
					if(data < 1){
						return '<span style="color:red;">Out of Stock!</span>';
					}
					return data;
				}
			},
			{
				data: 'id',
				nSortable: false,
				mRender: function(data,type,row){
					var str = '';
					str += '<a href="'+ window.contextRoot +'/show/'+ data +'/product" class="btn btn-secondary">View</a>';

					if(row.quantity < 1){
						str += ' <a href="javascript:void(0)" class="btn btn-danger disabled"><strike>Add to Cart</strike></a>';
					}else{
						str += ' <a href="'+ window.contextRoot +'/cart/add/'+ data +'/product" class="btn btn-success">Add to Cart</a>';
					}
					
					return str;
				}
			}
			
		]
	});
}