<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>Index Page</title>
<link rel="stylesheet" href="css/easy-responsive-tabs.css" type="text/css" /> 
<script type="text/javascript" src="admin/template/vendor/jquery.uploadfile.min.js"></script>
<link rel="stylesheet" href="admin/template/vendor/uploadfile.min.css" type="text/css" /> 

<script>

$(document).ready(function()
{
	 $("#fileuploader").uploadFile({
		url:"FileUpload",
		fileName:"myfile",
		showDone:false,
		showStatusAfterSuccess:false,
		maxFileSize: 50 * 1024 * 1024,
		sizeErrorStr:" Failed To Upload because max file size is exceeded. Max. File size is ",
		
		onSubmit:function(files)
		{
			$("#eventsmessage").html("Submitting:"+JSON.stringify(files));
		},
		onSuccess:function(files,data,xhr)
		{
			$("#eventsmessage").html("Success for: "+ JSON.stringify(data));
			
		},
		onError: function(files,status,errMsg)
		{
			$("#eventsmessage").html("Error for: "+ JSON.stringify(errMsg));
		}
		
		});
}); 

</script>

	<center>
        <form method="post" action="FileUpload" enctype="multipart/form-data">
        <p>Create new folder: <input type="text" id="txt_foldername" name="txt_foldername" class="chon"> </p>
            <div id="fileuploader">Upload</div>
             <div id="eventsmessage"></div>
          <!--   <input
             class="wfs_button" type="submit" value="Upload File" /> -->
        </form>
    </center>
