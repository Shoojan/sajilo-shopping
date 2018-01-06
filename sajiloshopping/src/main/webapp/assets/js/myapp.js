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
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;
		case 'User Cart':
			$('#userCart').addClass('active');
			break;
		default:
			if(menu == "Home") break;
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
	
	
	//To tackle the CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		
		//set token header for AJAX request
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	
	//code for jquery datatable
	var $table = $('#productListTable');

	//execute only where we have this table
	if($table.length){
//		console.log("i am inside table!");
		
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
						str += '<a href="'+ window.contextRoot +'/show/'+ data +'/product" class="btn btn-info"><span class="fa fa-eye"></span></a>';
						if(userRole == 'ADMIN'){
							str += ' <a href="'+ window.contextRoot +'/manage/'+ data +'/product" class="btn btn-warning"><span class="fa fa-edit"></span></a>';
						}
						
						if(userRole == 'USER'){
							if(row.quantity < 1){
								str += ' <a href="javascript:void(0)" class="btn btn-danger disabled"><strike><span class="fa fa-shopping-cart"></span></strike></a>';
							}else{
								str += ' <a href="'+ window.contextRoot +'/cart/add/'+ data +'/product" class="btn btn-success"><span class="fa fa-shopping-cart"></span></a>';
							}
						}
						
						return str;
					}
				}
				
			]
		});
	}


	//dismissing the alert after 3 sec
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000)
	}
	
	//--------------------------------------------------------------------
	
	/*
	 * DataTable For ADMIN
	 * */
	var $adminProductsTable = $('#adminProductsTable');

	//execute only where we have this table
	if($adminProductsTable.length){
//		console.log("i am inside table!");
		
		var jsonUrl = window.contextRoot + "/json/data/admin/all/products";
		
		$adminProductsTable.DataTable({
			lengthMenu: [[10,20,50,-1],['10','20','50','All']],
			pageLength: 20,
			ajax:{
				url: jsonUrl,
				dataSrc: '' //collection of objects without any name :)
			},
			columns:[
				{
					data: 'id'
				},
				{
					data: 'code',
					mRender: function(data, type, row){
						return '<img src="'+ window.contextRoot +'/resources/images/' +data+ '.jpg" class="adminDataTableImg"/>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
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
					data: 'unitPrice',
					mRender: function(data,type,row){
						return 'Rs. ' + data;
					}
				},
				{
					data: 'active',
					nSortable: false,
					mRender: function(data,type,row){
						var str = '';
						str += '<label class="switch">';
						if(data){
							str += '<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						}else{
							str += '<input type="checkbox" value="'+row.id+'"/>';
						}
						str += '<div class="slider"></div></label>';
						
						return str;		
						
					}
				},
				{
					data: 'id',
					nSortable: false,
					mRender: function(data,type,row){
						var str='';
						str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str += '<span class="fa fa-edit"></span>';
						str += '</a>';
						return str;
					}
				}
				
			],
			initComplete: function(){
				var api = this.api();
				//Admin -> switch-active
				api.$('.switch input[type="checkbox"]').on('change', function(){
					
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)?"Do you want to activate the product?":"Do you want to deactivate the product?";
					
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size: 'medium',
						title: 'Product Activation/Deactivation',
						message: dMsg,
						callback: function(confirmed){
							if(confirmed){
								console.log(value);
								
								//Product activation/deactivation using jquery AJAX
								var activationUrl = window.contextRoot+'/manage/product/'+value+'/activation';
								$.post(activationUrl, function(data){
									bootbox.alert({
										size: 'medium',
										title: 'Confirmation',
										message: data
									});
								});
								
								
								
							}else{
								checkbox.prop('checked',!checked);
							}
						}
					});
				});
			}
		});
	}
	//----------------------------------------------------------
	
	/*
	 * Validation Code for Category
	 * */
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
			rules:{
				name:{
					required:true,
					minlength:2
				},
				description:{
					required:true
				}
			},
			messages:{
				name:{
					required:'Category name is empty!',
					minlength:'Should not be less than 2 characters!'
				},
				description:{
					required:'Description field is empty!'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element){
				//add the class of help-block
				error.addClass('help-block');
				//add error element after input element
				error.insertAfter(element);
			}
		});
	}
	//---------------------------------------------------------------
	
	/*
	 * Validation Code for loginForm
	 * */
	var loginForm = $('#loginForm');
	if(loginForm.length){
		loginForm.validate({
			rules:{
				email:{
					required:true,
					email:true
				},
				password:{
					required:true
				}
			},
			messages:{
				email:{
					required:'Please Enter Username!',
					email:'Invalid Email id!'
				},
				description:{
					required:'Password Incorrect!'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element){
				//add the class of help-block
				error.addClass('help-block');
				//add error element after input element
				error.insertAfter(element);
			}
		});
	}
	
	
});

/*
 * Handling the Click Event of the refresh cart button
 * */
$('button[name="refreshCart"]').click(function(){
	
	//fetch the cart line id
	var cartLineId = $(this).attr('value');
	var countElement = $('#count_' + cartLineId);
	
	var originalCount = countElement.attr('value');
	var currentCount = countElement.val();
	
	//work only when the count has changed
	if(currentCount !== originalCount){
		
//		console.log("Current Count:" + currentCount);
//		console.log("Original Count:" + originalCount);
		
		if(currentCount < 1 || currentCount > 3){
			//reverting back to the original count
			countElement.val(originalCount);
			bootbox.alert({
				size: 'medium',
				title: 'Error',
				message: 'Product count should be minimum 1 or maximum 5!'
			})
		}
		else{
			var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
			
			//forward it to the controller
			window.location.href = updateUrl;
		}
	}
});


