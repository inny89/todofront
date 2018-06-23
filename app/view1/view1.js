'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$http', function($http) {

  var self = this;
  this.daneZSerwera = {};

  this.funkcja1 = function () {
      console.log('klik!');
      $http.get('http://192.168.130.41:8081/task')
          .then(function (dane) {
              console.log(dane);
              self.daneZSerwera = dane;
          })
  }

}]);