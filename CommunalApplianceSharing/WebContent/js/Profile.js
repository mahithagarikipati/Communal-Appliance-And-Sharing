/**
 * 
 */
var profileapp = angular.module('profile', ["ngMessages","ngMaterial","ngAria"]);
profileapp.controller("getData", [ '$scope', '$http','$window','$mdDialog', '$parse',function($scope, $http,$window,$mdDialog,$parse) {
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
    $scope.init = function(ev) {
        $http({
            url : 'ProfileService',
            method : "POST",
            params : {
                'userName' : userName
            }
        }).then(function(response,$window) {
            $scope.firstName = response.data.firstName;
            $scope.lastName = response.data.lastName;
            $scope.username = response.data.userName;
            $scope.add = response.data.address;
            $scope.Phone = response.data.phone_no;
            $scope.Password = response.data.password;
            $scope.CPassword = response.data.password;
            $scope.Email = response.data.email;
            $scope.Zipcode = response.data.zipcode;
            
            $scope.firstname = response.data.firstName;
            $scope.lastname = response.data.lastName;
            $scope.UserName = response.data.userName;
            $scope.address = response.data.address;
            $scope.phone = response.data.phone_no;
            $scope.password = response.data.password;
            $scope.confirmPassword = response.data.password;
            $scope.email = response.data.email;
            $scope.zipcode = response.data.zipcode;
            console.log(response.data);
            })
    }

}]);
profileapp.controller("sendData", [ '$scope', '$http','$window','$mdDialog', '$parse',function($scope, $http,$window,$mdDialog,$parse) {
$scope.showConfirm = function(ev) {
    var confirm = $mdDialog.confirm()
      .title('Confirm Update')
      .textContent('Are you sure you want to update?')
      .ariaLabel('Update')
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
          url : 'ProfileService',
          method : "POST",
          data : {
              'firstname' : $scope.firstName,
              'lastname':$scope.lastName,
              'username' :userName,
              'phone':$scope.phone,
              'psw' : $scope.password,
              'pswr':$scope.password,
              'address' : $scope.address,
              'zipcode':$scope.zipcode,
              'email' : $scope.email,
              'mode':"UPDATE"
          }
      }).then(function(response,$window) {
          console.log(response.data);
          $scope.message = response.data;
          if (($scope.message ) == "SUCCESS") {
         var check=     $mdDialog.alert()
                        .parent(angular.element(document.querySelector('#popupContainer')))
                        .clickOutsideToClose(true)
                        .title('Update Success')
                        .textContent('Thank You ! Your Update Has been Successful')
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
                        .title('Update Failure')
                        .textContent('Due to technical issue we cannot process your update at this time. Please try again later!')
                        .ariaLabel('Alert Dialog Demo')
                        .ok('Close')
                        .targetEvent( );
          $mdDialog.show(check).then(function () {
                                          });

          return ;
          }
      });        
};
}]);

