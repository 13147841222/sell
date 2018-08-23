package com.dream.sell.handler;

import com.dream.sell.VO.ResultVO;
import com.dream.sell.config.ProjectUrlConfig;
import com.dream.sell.exception.ResponseBankException;
import com.dream.sell.exception.SellException;
import com.dream.sell.exception.SellerAuthorizeException;
import com.dream.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhumingli
 * @create 2018-08-05 下午10:42
 * @desc
 **/
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 拦截登陆异常
     * @return
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){

        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWeChatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getProjectUrl())
                .concat("/sell/seller/login")
        );
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){

        return ResultVOUtil.error(e.getCode() ,e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(){

    }
}
