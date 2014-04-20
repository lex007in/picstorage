'use strict';
angular.module('picstorage', [
  'ngRoute',
  'ngResource',
  'picstorage.controllers'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/upload', {templateUrl: 'partials/upload.html', controller: 'UploadCtrl'});
  $routeProvider.when('/', {templateUrl: 'partials/pictures.html', controller: 'PicturesCtrl'});
  $routeProvider.when('/my', {templateUrl: 'partials/pictures.html', controller: 'PicturesCtrl'});
  $routeProvider.when('/pic/:picId', {templateUrl: 'partials/picDetail.html', controller: 'PicDetailCtrl'});
  $routeProvider.otherwise({redirectTo: '/'});
}]);