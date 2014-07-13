OrdShtPayCardClass.onChangeCardKind = function(){}


OrdShtPayCardClass.onChangeCardKind = function(){



  //  if (jQuery("#myShinPnt").is(':visible') == true){  //iframe 열려 있으면 닫아주기
        OrdShtDcClassPop.openMyShPnt();
    //}

}



OrdShtDcClassPop.openMyShPnt = function(){

    OrdShtDcClassPop.iFrameCommonOpen("myShinPnt", "");
}




// iframe 생성(공통)
OrdShtDcClassPop.iFrameCommonOpen = function(sub_box, url) {

    var targetObj = jQuery("#"+sub_box);
    var parentDl = targetObj.parent();

    OrdShtDcClassPop.iFrameCommonClose(sub_box);

    OrdShtDcUtil.closeMyDialog("Progressive_Div");

}




OrdShtDcClassPop.iFrameCommonClose = function(sub_box, forceDel){
    console.log("여기 선택됨");

    var targetObj = jQuery("#"+sub_box);
    var iframeObj = jQuery("#"+sub_box+"_iframe");


    jQuery('#'+sub_box).hide();


    if(forceDel == undefined) forceDel = false;
    if(sub_box != "myShinPnt" || forceDel) { // 신한포인트는 프레임 제거 안함
        if(iframeObj.length > 0){
            iframeObj.remove();
        }
    }

    //iframe 닫으면서 할인금액이 0 이면 체크박스 해제
    if(sub_box == "coupon"){
        if( jQuery("#dc_txt_cpn_amt").val() == "0" && jQuery("#cpn_chk").is(":checked") == true ){
            jQuery("#cpn_chk").attr("checked", false);
        }
        //조회버튼 화살표 처리
        jQuery("#coupon_btn").removeClass("close");
        //조회버튼 노출명 처리
        jQuery("#coupon_btn_nm").html("조회 및 사용");
    }else if(sub_box == "freeCoupon"){
        if( jQuery("#dc_dlvc_cpn_amt").val() == "0" && jQuery("#dlvc_cpn_chk").is(":checked") == true ){
            jQuery("#dlvc_cpn_chk").attr("checked", false);
        }

        //조회버튼 화살표 처리
        jQuery("#dlvc_cpn_btn").removeClass("close");
        //조회버튼 노출명 처리
        jQuery("#freeCoupon_btn_nm").html("조회 및 사용");
    }else if(sub_box == "myShinPnt"){
        //if( jQuery("#pay_txt_mysh_amt").val() == "0"){
        //	jQuery("#mysh_chk").attr("checked", false);
        //}
        //조회버튼 화살표 처리
        jQuery("#myShinPnt_btn").removeClass("close");
        //조회버튼 노출명 처리
        if( jQuery("#pay_txt_mysh_amt").val() == "0")
            jQuery("#myShinPnt_btn_nm").html("조회 및 사용");
        else
            jQuery("#myShinPnt_btn_nm").html("수정");
    }else if(sub_box == "giftCard"){
        jQuery("#giftcard_chk").attr("checked", false);
    }
}




OrdShtDcUtil.closeMyDialog= function(id){
        jQuery("#"+id).hide();
}