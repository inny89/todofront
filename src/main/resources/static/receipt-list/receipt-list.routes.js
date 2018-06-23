angular.module('receipt-list')
.config(function ($routeProvider) {
    $routeProvider.when('/receipts',{
        templateUrl: '/receipt-list/receipt-list.html',
        controller: 'ReceptListController',
        controllerAs: 'controller'
    })
})