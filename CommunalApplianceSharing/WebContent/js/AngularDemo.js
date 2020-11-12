var userLogin = angular.module("login", ["ngMessages","ngMaterial","ngAria"]);
userLogin.controller("registerController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
 $scope.sendData = function() {
          $http({
              url : 'LoginService',
              method : "POST",
              data : {
            	  'username' : $scope.login.UserName, 
                  'password' : $scope.password
               
              }
              }).then(function(response,$window) {
              console.log(response.data);
              $scope.message = response.data;
              if (($scope.message ) == "SUCCESS") {
             var check=     $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)
                            .title('Login Success')
                            .textContent('Thank You ! Your login Has been Successful')
                            .ariaLabel('Close')
                            .ok('Close')
                            .targetEvent( );
               $mdDialog.show(check).then(function () {
                   window.location.href = "home.html?userName="+$scope.login.UserName;
                   });
              }
              else{
                  var check=     $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)
                            .title('Login Failure')
                            .textContent('Incorrect Username Or Password!')
                            .ariaLabel('Alert Dialog Demo')
                            .ok('Close')
                            .targetEvent( );
              $mdDialog.show(check).then(function () {
                                              });
    
              return ;
              }
          });        
    };

} ]);
