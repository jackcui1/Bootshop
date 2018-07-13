
var cartApp = angular.module ("cartApp", []);

cartApp.controller("cartCtrl", function ($scope, $http){

    $scope.refreshCart = function () {
        $http.get('/rest/cart/get').success(function (data) {
           $scope.cart=data;
//           $scope.updateCartGrandTotal();
//           $scope.updateCartItemCount();
        });
    };

    $scope.init = function () {
        $scope.refreshCart();
    };
    
//    $scope.addToCart = function (productId) {
//        $http.put('/rest/cart/add/'+productId).success(function () {
//        	$scope.refreshCart();
//        });
//    };
    
    $scope.removeFromCart = function (productid) {
        $http.get('/rest/cart/remove/'+productid).success(function (data) {
            $scope.refreshCart();
        });
   };
   
    $scope.clearCart = function () {
        $http.get('/rest/cart/remove/all').success(function (data) {
            $scope.refreshCart($http.get('/rest/cart/get'));
        });
    };
	
//    $scope.updateCartItemCount = function () {
//        $http.get('/rest/cart/items/count').success(function (data) {
//        });
//    };
//    
//    $scope.updateCartGrandTotal = function () {
//        $http.get('/rest/cart/items/grandtotal').success(function (data) {
//        });
//    };
    
});