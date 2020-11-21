/**
 * 
 */

 var notification= angular.module("notification", ["ngMessages","ngMaterial","ngAria"]);

 notification.controller("notificationController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
     $scope.addFields = function(ev) {
     $http({
         url : 'NotificationService',
         method : "POST",
         params : {
             'username' : userName
         }
     }).then(function(response,$window) {
        
         console.log(response.data);
      
         $scope.array = response.data;
         var number = response.data.length; 
         var container = document.getElementById("container");
         while (container.hasChildNodes()) {
             container.removeChild(container.lastChild);
         }
         for (i=0;i<number;i++){
         var para = document.createElement("p");
          var input = document.createElement("textarea");
             input.maxLength ="500";
             input.rows ="4";
              input.setAttribute('ng-model',response.data[i].appliance_id);
              input.value =response.data[i].firstName+" "  +response.data[i].lastName+"  ("+response.data[i].userName+")"+"\r\n"
              +"Item listed for Lending : " +response.data[i].appliance_name+"\r\n"+response.data[i].appliance_desc;
             var id=response.data[i].appliance_id;
             para.appendChild(input);
             container.appendChild(para);
             
         }
         if(number==0){
             var input = document.createElement("p");
              input.innerText ="No notifications to display!";
             container.appendChild(input);
             container.appendChild(document.createElement("br"));
             
         }
         })
     }        
}]);