'use strict';
angular.module('picstorage.controllers', [])
  .controller('UploadCtrl', function($scope, $http) {
      $scope.setFile = function(file) {
          $scope.picture.file = file;
      };
      $scope.upload = function() {
          var fd = new FormData();
          fd.append('title', $scope.picture.title);
          fd.append('description', $scope.picture.description);
          fd.append('file', $scope.file.files[0]);
          $http.post("/rest/pictures", fd,{
              transformRequest: angular.identity,
              headers: {
                'Content-Type': undefined
              }
            }).success(function(user) {
              alert("File uploaded!");
          });;
      };
  })
  .controller('PicturesCtrl', function($scope, $location, $http) {
      $scope.pictures = [];
      $http.get('/rest/pictures').success(function(data) {
          if ($location.absUrl().endsWith("/my")) {
              $http.get('/rest/current').success(function(user) {
                  $scope.pictures = data.filter(function(a){return a.creator.login === user.login;});
              });
          } else {
            $scope.pictures = data;
          }
      });
  })
  .controller('PicDetailCtrl', function($scope, $location,  $routeParams, $http) {
      $scope.picture = {};
      var updateData = function(data) {
          $scope.picture = data;
          $scope.picture.printDate = new Date(data.creationTime).toString();
          $scope.picture.comments.map(function(comment) { comment.printDate = new Date(data.creationTime).toString();});
          $http.get('/rest/current').success(function(user) {
                  if ($scope.picture.creator.login === user.login) {
                      $('#delButton').removeClass('hide');
                  };
              });
      };
      $http.get('/rest/pictures/' + $routeParams.picId).success(updateData);
      $scope.addComment = function() {
          var fd = new FormData();
          fd.append('text', $scope.comment.text);
          $http.post("/rest/pictures/" + $scope.picture.id + "/addcomment", fd,{
              transformRequest: angular.identity,
              headers: {
                'Content-Type': undefined
              }
            }).success(updateData);
      }
      $scope.delete = function() {
          $http.delete("/rest/pictures/" + $scope.picture.id).success(function(user) {
              $location.path("/");
              $scope.$apply();
          });
      }
  });