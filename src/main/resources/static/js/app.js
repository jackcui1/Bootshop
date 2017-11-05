jQuery(document).ready(function($){
	updateCartItemCount();
	updateCartGrandTotal()
});

function addItemToCart(productid)
{
	$.ajax({
		  url: "/rest/cart/add/"+productid,
		  type: "GET", 
		  data: { 
		    productid: productid, 
		  },
		  success: function(response) {
			  updateCartItemCount();
			  updateCartGrandTotal();
		  },
		  error: function(xhr) {
		  }
		});
}

function updateCartItemCount()
{
	$.ajax ({ 
		url: '/rest/cart/items/count', 
		type: "GET", 
		dataType: "json",
		contentType: "application/json",
		complete: function(responseData, status, xhttp){ 
			$('#cart-item-count').text('['+responseData.responseJSON.count+'] Itemes in your cart');
		}
		});
	}

function updateCartGrandTotal()
{
	$.ajax ({
			url: '/rest/cart/items/grandtotal', 
			type: "GET", 
			dataType: "json",
			contentType: "application/json",
			complete: function(responseData, status, xhttp){ 
				$('#cart-item-grandtotal').text('C$ '+responseData.responseJSON.grandtotal);
			}
	});
}