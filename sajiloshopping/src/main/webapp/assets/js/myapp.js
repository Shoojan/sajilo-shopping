$(function(){
	//To solve the active menu focus problem
	switch(menu){	
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact Us':
			$('#contact').addClass('active');
			break;
		case 'View Products':
			$('#listProducts').addClass('active');
			break;
		default:
			$('$home').addClass('active');
			break;
	}
});