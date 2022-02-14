<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script src="/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<style>
.box-head{
 	border-radius: 10px;
 	border: 2px solid #87c7ed;
 	margin-top: 2%;
 	height:4%;
 	width: 80%;
 	margin-left: 10%;
 	background: #bfefef;
 	box-shadow: 0 0 0.1cm rgba(0,0,0,0.5);
}
.box-top{
	border: 3px solid #87c7ed;  margin-top: 2%; margin-left: 10%; border-radius: 10px;
	width: 80%;
}
.font-normal-bold{
	font-size: 12px;
	font-family: sans-serif ;
	font-weight: bold;
}

</style>
<div class="container-fluid bg-img">
	<div class="dash-right-content">
<!-- 	<div class="row justify-content-center" style="background-image: linear-gradient(to right,  #93a8f1 , #87c7ed); height: 10%;"><h4 class="font-big-bold pt-3">Online Complaint</h4> -->
<!-- 	</div> -->
	<hr class="m-0 p-0">
	<div class="row box-head"><h5 style="padding-left: 40%; margin: 1px;" >Users</h5></div>
	</div>
	<div class="box-top">
		<br>
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-2">
				<span>User Name</span>
				<select id="useLst" class="form-control"></select>
			</div>
			<div class="col-lg-1">
			<br>
				<button class="btn btn-primary btn-sm" onclick="$(this).fetchUserDtl()" value="Fetch">Fetch</button>
			</div>
		</div>
		<div class="col-lg-12" >
			<div id="result">
			
			</div>
		
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	var list;
	$.ajax({
		type : 'GET',
		url  : '/userList',
		processlist: false,
        contentType: false,
        async: false,
		success : function(suclist){
			if(suclist !='[]' && suclist != null){
				populdateUserName(suclist);
				list=suclist;
			}
		}
	})
	
	function populdateUserName(list){
		list=list;
		console.log(list);
		for(var i=0; i< list.length; i++){
			$("#useLst").append('<option value="'+list[i].id+'">'+list[i].name+'</option>')
		}
	}
	
	$.fn.fetchUserDtl = function(){
		var userId = $("#useLst").val();
		var htmlData;
		for(var i=0; i< list.length; i++){
			if(userId==list[i].id){
				htmlData='<div class="row pt-5"></div><div class="row"><div class="col-lg-1"><label>Name:</label></div>'
				+'<div class="col-lg-2"><input type="text" readonly value="'+list[i].name+'"</div></div></div><br>'
				+'<div class="row"><div class="col-lg-1"><label>Address:</label></div>'
				+'<div class="col-lg-2"><input type="text" readonly value="'+list[i].address+'"</div></div></div><br>'
				+'<div class="row"><div class="col-lg-1"><label>Number:</label></div>'
				+'<div class="col-lg-2"><input type="text" readonly value="'+list[i].number+'"</div></div></div><br>'
				+'<div class="row"><div class="col-lg-1"><label>Salary:</label></div>'
				+'<div class="col-lg-2"><input type="text" readonly value="'+list[i].salary+'"</div></div></div><br>'
				+'<div class="row"><div class="col-lg-1"><label>Pension:</label></div>'
				+'<div class="col-lg-2"><input type="text" readonly value="'+list[i].pension+'"</div></div></div><br>';
				$("#result").html(htmlData);
				break;
			}
		}
		console.log(list);
		
	}
	
})
</script>