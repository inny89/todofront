var receiptListModule = angular.module('receipt-add');

receiptListModule.config(function ($routeProvider) {
    $routeProvider.when('/receipts/add',{
        templateUrl: '/receipt-add/receipt-add.html',
        controller: 'receiptAddController',
        controllerAs: 'controller'
    })
})