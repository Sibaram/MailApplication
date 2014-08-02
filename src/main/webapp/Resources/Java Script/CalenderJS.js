/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function goPreviousMonth(prev){
    
    var screenDate=$("#CalScreDate").val();
    //alert("prev---"+prev+screenDate)
    var url = "../mail/AirFareCalendar.jsp";
    $.ajax({
        url: url,
        type: 'POST',
        async: true,
        data: "go="+prev+"&screenDate="+screenDate,
        success: function(data) {
            $("#dumimageCal").html(data);
        }
    });
}

function goNextMonth(next){
    
    var screenDate=$("#CalScreDate").val();
    //alert("prev---"+next+screenDate)
    var url = "../mail/AirFareCalendar.jsp";
    $.ajax({
        url: url,
        type: 'POST',
        async: true,
        data: "go="+next+"&screenDate="+screenDate,
        success: function(data) {
            $("#dumimageCal").html(data);
        }
    });
}
