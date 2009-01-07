 function go_to1(ao,h,d){
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
 
 function del(creator)
{
creator.target="preview";
creator.action="vote.jsp";
var win = window.open("about:blank","preview","toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600,height=260,top=200,left=200");
win.focus();
creator.submit();
} 