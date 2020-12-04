/**
 * 
 */

 var home= angular.module("home", ["ngMessages","ngMaterial","ngAria"]);

 home.controller("homeController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
     $scope.addFields = function(ev) {
     $http({
         url : 'HomeService',
         method : "POST",
         params : {
             'userName' : userName
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
            var button = document.createElement("input");
            button.type = "button";
             button.value = "Details";
             var id = response.data[i].appliance_id;
             var userName1 = response.data[i].userName;
             button.onclick =( function (userName1, id,userName) {
                 return function(){
                 window.location.href = "ApplianceDetails.html?userName="+userName1+"&appId="+id+"&userlogin="+userName;   }       })(userName1, id,userName);
             para.appendChild(input);
             para.appendChild(button);
             container.appendChild(para);
             
         }
         if(number==0){
             var input = document.createElement("p");
              input.innerText ="No items listed in your community for borrowing. Please check again in some time!";
             container.appendChild(input);
             container.appendChild(document.createElement("br"));
             
         }
         })
     }        
}]);
