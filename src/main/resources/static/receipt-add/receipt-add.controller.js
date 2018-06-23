angular.module('receipt-add')
    .controller('receiptAddController', function(receiptService, $location){

        var controller = this;

        controller.receipt = {};

        controller.save = save;

        function save(){
            receiptService.create(controller.receipt).then(function () {
                $location.path('/receipts')
            });
        }
});