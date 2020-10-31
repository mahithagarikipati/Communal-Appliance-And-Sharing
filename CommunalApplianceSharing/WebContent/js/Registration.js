 var userRegistration = angular.module("registration", ["ngMessages","ngMaterial","ngAria"]);
 
 userRegistration.controller("registerController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
 
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
    $scope.showConfirm = function(ev) {
        var confirm = $mdDialog.confirm()
          .title('Do you want to continue with the registration?')
          .textContent('You agree that by registering into our application, you have read, understood, and agree to be bound by all of these Terms and Conditions displayed in our home page. If you do not agree with all of these Terms and Conditions, then you are expressly prohibited from using the Site and you must discontinue use immediately.')
          .ariaLabel('Registered') 
          .targetEvent(ev)
          .ok('Yes')
          .cancel('No');
  $mdDialog.show(confirm).then(function () {
      sendData();
        }, function () {
        });
      };
      
      sendData = function() {
          $http({
              url : 'RegistrationService',
              method : "POST",
              data : {
                  'firstname' : $scope.firstname,
                  'lastname':$scope.lastname,
                  'username' : $scope.registration.UserName,
                  'phone':$scope.phone,
                  'psw' : $scope.registration.user.password,
                  'pswr':$scope.registration.user.confirmPassword,
                  'address' : $scope.address,
                  'zipcode':$scope.zipcode,
                  'email' : $scope.email,
                  'mode':"ADD"
              }
          }).then(function(response,$window) {
              console.log(response.data);
              $scope.message = response.data;
              if (($scope.message ) == "SUCCESS") {
             var check=     $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)
                            .title('Registration Success')
                            .textContent('Thank You ! Your Registration Has been Successful')
                            .ariaLabel('Close')
                            .ok('Close')
                            .targetEvent( );
                           
             $mdDialog.show(check).then(function () {
     location.reload();
                   });
              }
              else{
                  var check=     $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)
                            .title('Registration Failure')
                            .textContent('Due to technical issue we cannot process your account at this time. Please try again later!')
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
 
     userRegistration.directive('tradeUniqueUserName', ['accounts', function (accounts) {
         return {
             restrict: 'A',
             require: 'ngModel',
             link: function (scope, elm, attrs, ctrl) {
                 if (!ctrl) return;
                 elm.bind('blur', function () {
                     scope.$apply(checkUserName);
                 });
                 var checkUserName = function () {
                     var userName = elm.val();
                     accounts.checkUserName(userName).then(function (result) {
                         if(result.found=="false"){
                             ctrl.$setValidity('tradeUniqueUserName', !result.found);
                         }
                         if(result.found=="true"){
                             ctrl.$setValidity('tradeUniqueUserName', true);
                         }
                     });
                     return userName;
                 };
             }
         };
     }]);
     userRegistration.factory('accounts', ['$q', '$http', '$log', function ($q, $http, $log) {
         var checkUserName = function (name) {
             var action = "RegistrationService";
             return $http(
                 { method: 'POST', url: action, params: { userName: name } })
                 .then(
                 function (result) {
                     if(result.data=="INVALID")  result.data="false";
                     if(result.data=="SUCCESS")  result.data="true";
                     return { code: 200, found: result.data}
                 });
         }
         return {
             checkUserName: checkUserName
         };
     }]);
     

     var compareTo = function() {
       return {
         require: "ngModel",
         scope: {
           otherModelValue: "=compareTo"
         },
         link: function(scope, element, attributes, ngModel) {

           ngModel.$validators.compareTo = function(modelValue) {
             return modelValue == scope.otherModelValue;
           };

           scope.$watch("otherModelValue", function() {
             ngModel.$validate();
           });
         }
       };
     };

     userRegistration.directive("compareTo", compareTo);


/**
 * 
 */