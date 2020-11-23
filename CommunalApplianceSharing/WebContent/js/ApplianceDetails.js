/**
 * 
 */

var details= angular.module("details", ["ngMessages","ngMaterial","ngAria"]);

details.controller("detailsController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
     $scope.addFields = function(ev) {
     $http({
         url : 'ApplianceService',
         method : "POST",
         params : {
             'userName' : userName,
             'appId' : id
         }
     }).then(function(response,$window) {
        
         console.log(response.data);
         $scope.object = response.data;
         var container = document.getElementById("container");
         while (container.hasChildNodes()) {
             container.removeChild(container.lastChild);
         }
         var para = document.createElement("p");
                  var input = document.createElement("textarea");
             input.maxLength ="500";
             input.rows ="5";
              input.setAttribute('ng-model',response.data.id);
              input.value = "Lender's Name: " +response.data.firstName+" "+response.data.lastName+" "+"\r\n"
			  + "Lender's Address: " +response.data.streetAddress+"\r\n" + "Phone Number: " +response.data.phoneNo+" "+"\r\n" 
			  + "Price: " +response.data.price_per_day+"\r\n" + "Available Dates: " +response.data.available_from_dt+" - "+response.data.available_to_dt;
            para.appendChild(input);
            container.appendChild(para);
		})
	}       
}]);
