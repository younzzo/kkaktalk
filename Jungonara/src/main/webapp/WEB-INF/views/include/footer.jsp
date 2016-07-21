
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



<!-- footer begin -->

  <!-- Pagination 
  <div class="w3-center w3-padding-32">
    <ul class="w3-pagination">
      <li><a class="w3-black" href="#">1</a></li>
      <li><a class="w3-hover-black" href="#">2</a></li>
      <li><a class="w3-hover-black" href="#">3</a></li>
      <li><a class="w3-hover-black" href="#">4</a></li>
      <li><a class="w3-hover-black" href="#">&raquo;</a></li>
    </ul>
  </div>

	-->

<script>
// Script to open and close sidenav




  function delete_image() {
		alert("삭제버튼 눌러짐");
		//form2.action="/trade/" +form2.keyfield.value +"/"+form2.keyword.value;
		var img = document.getElementById('delete_img');
		img.submit();
  }

  function goSearch() {
		//alert("검색버튼 눌러짐");
		//form2.action="/trade/" +form2.keyfield.value +"/"+form2.keyword.value;
		form2.submit();
    }
    
//  $(document).ready(function(){
//	  $("#show_addFile").click(function(){
//		  $("p").show();
//	  });
 // })
  
	function create_addFile(){
		
		 var pp = document.createElement("P");
		 var btn = document.createElement("input");
		     btn.setAttribute('type', 'file');
		     btn.setAttribute('name', 'file');
		     btn.setAttribute('multiple', 'multiple');
		     pp.appendChild(btn);
		     document.getElementById("addFile").appendChild(pp);
		     $("#plus").hide();
		     //addFile.style.display='none';
		
		
		     
	//	 var af = document.getElementById('addFile');
	//	   	 af.appendChild(btn);
	//	     document.body.appendChild(btn);
	
		
//		var af=document.getElementById("addFile");
//			if(af.style.display='none')
//				af.style.display='';	
	}

function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}


function myFunction() {
    var x = document.getElementById("search");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}

</script>


</body>
</html>
<!-- footer end -->