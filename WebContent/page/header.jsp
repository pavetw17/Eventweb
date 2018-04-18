<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}
</script>
<script>
 /*  (function() {
    var cx = '009323578787078392120:4_jmr-kmcwi';
    var gcse = document.createElement('script');
    gcse.type = 'text/javascript';
    gcse.async = true;
    gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') +
        '//www.google.com/cse/cse.js?cx=' + cx;
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(gcse, s);
  })(); */
</script>

<div id="templatemo_header">
        
        <div id="site_title"><h1><a href="index.jsp" target="_parent"></a></h1></div>
        
    </div>
<div id="templatemo_menu">
        <ul>
            <li><a href="RedirectPage?action=aboutus" >About Us</a></li>
            <li><a href="RedirectPage?action=highlights" >News</a></li>
            <li><a href="RedirectPage?action=events" >Events</a></li>
            <li><a href="RedirectPage?action=project" >Project Information</a></li>
            <li><a href="RedirectPage?action=publications" >Publications</a></li>
            <li><a href="RedirectPage?action=workplan" >Work Plan</a></li>
        </ul> 
        
        <div id="search_box">
            <form action="SearchBox" method="post">
                <input type="text" value="Enter keyword here..." name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)">
                <input type="submit" name="Search" value="" id="searchbutton" title="Search">

            </form>
        </div>   	
        <div class="cleaner"></div>
    </div>

