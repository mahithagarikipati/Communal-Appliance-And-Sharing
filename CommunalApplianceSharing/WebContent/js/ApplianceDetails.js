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
             'appId' : appId,
             'mode':'GETDATA'
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
              input.setAttribute('ng-model',response.data.appliance_id);
              input.value = "Lender's Name: " +response.data.firstName+" "+response.data.lastName+" "+"\r\n"
			  + "Lender's Address: " +response.data.streetAddress+"\r\n" + "Phone Number: " +response.data.phoneNo+" "+"\r\n" 
			  + "Price: " +response.data.price_per_day+"\r\n" + "Available Dates: " +response.data.available_from_dt+" to "+response.data.available_to_dt;
            var button = document.createElement("input");
            var button2 = document.createElement("input");
            button.type = "button";
            button.value = "Interested";
            button2.type = "button";
            button2.value = "Back";
            button2.onclick =( function (loginDet) {
                return function(){
                window.location.href = "home.html?userName="+loginDet;   }       })(loginDet);
            para.appendChild(input);
           // para.appendChild(button2)
            //para.appendChild(button);
            container.appendChild(para);
		})
	}     ;
	
	 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
	 $scope.sendData = function() {
	          $http({
	              url : 'ApplianceService',
	              method : "POST",
	              params  : {
	                  'userName' : userName, 
	                  'appId' : appId,
	                  'borrowerName':loginDet,
	                  'mode':'ADD'
	              }
	              }).then(function(response,$window) {
	              console.log(response.data);
	              $scope.message = response.data;
	              if (($scope.message ) == "SUCCESS") {
	             var check=     $mdDialog.alert()
	                            .parent(angular.element(document.querySelector('#popupContainer')))
	                            .clickOutsideToClose(true)
	                            .title('Interested')
	                            .textContent('Lender has been Notified')
	                            .ariaLabel('Close')
	                            .ok('Close')
	                            .targetEvent( );
	               $mdDialog.show(check).then(function () {
	                   window.location.href = "home.html?userName="+loginDet;
	                   });
	              }
	              else{
	                  var check=     $mdDialog.alert()
	                            .parent(angular.element(document.querySelector('#popupContainer')))
	                            .clickOutsideToClose(true)
	                            .title('Notify Failure')
	                            .textContent('Lender couldnot be notified at this time. Please try again in some time')
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
