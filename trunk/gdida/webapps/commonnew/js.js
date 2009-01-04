 function go_to(ao,h,d){
  for(var i=0;i<h.length;i++){
   if(ao-1==i){
   h[i].className+=" up";
   d[i].className+=" block";
   }
   else {
   h[i].className=" ";
   d[i].className=" ";
   }
  }
 }