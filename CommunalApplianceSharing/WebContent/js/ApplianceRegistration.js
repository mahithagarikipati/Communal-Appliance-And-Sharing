var applianceRegistration = angular.module("appliance", ["ngMessages","ngMaterial","ngAria"]);
 applianceRegistration.controller("registerAppliance", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
    $scope.showConfirm = function(ev) {
        var confirm = $mdDialog.confirm()
          .title('Do you want to continue with registering this appliance?')
          .ariaLabel('Registered')
          .targetEvent(ev)
          .ok('Yes')
          .cancel('No');
  $mdDialog.show(confirm).then(function () {
      sendData();
        }, function () {
        });
      };
      
      //POST method used to Insert new data based on given URL
      sendData = function() {
          $http({
              url : 'ApplianceRegistrationService',
              method : "POST",
              data : {
                  'username' : $scope.userName,
                  'appliance_name':$scope.appliance_name,
                  'appliance_desc' : $scope.appliance_desc,
                  'available_from_dt':$scope.available_from_dt,
                  'available_to_dt' : $scope.available_to_dt,
                  'price_per_day':$scope.price_per_day,
                  'mode':"ADD"
              }
          }).then(function(response,$window) {
              console.log(response.data);
              $scope.message = response.data;
              if (($scope.message ) == "SUCCESS") {
             var check=     $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)
                            .title('Appliance Registration Success')
                            .textContent('Thank You! Your appliance has been successfully registered')
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
                            .title('Appliance Registration Failure')
                            .textContent('Due to technical issue we cannot process your appliance registration at this time. Please try again later!')
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
 /*
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
	 */
     applianceRegistration.factory('accounts', ['$q', '$http', '$log', function ($q, $http, $log) {
         var checkAppName = function (name) {
             var action = "ApplianceRegistrationService";
             return $http(
                 { method: 'POST', url: action, params: { appName: name } })
                 .then(
                 function (result) {
                     if(result.data=="INVALID")  result.data="false";
                     if(result.data=="SUCCESS")  result.data="true";
                     return { code: 200, found: result.data}
                 });
         }
         return {
             checkAppName: checkAppName
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

     applianceRegistration.directive("compareTo", compareTo);
