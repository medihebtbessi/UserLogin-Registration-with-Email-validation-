package find_your_house.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import find_your_house.payment.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("payment")
public class PaypalController {

    private final PaypalService paypalService;

    @GetMapping("/")
    public String home(){
        return "templates/index.html";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description
    ){
        try {
            String cancelUrl="http://localhost:8090/payment/cancel";
            String successUrl="http://localhost:8090/payment/success";
            Payment payment=paypalService.createPayment(
                  Double.valueOf(amount),
                  currency,
                  method,
                  "sale",
                  description,
                  cancelUrl,
                  successUrl
            );
            for (Links links :payment.getLinks()){
                if (links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }
        }catch (PayPalRESTException e){
            log.error("Error occurred:: ",e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerId") String payerId
    ){
        try {
            Payment payment=paypalService.executePayment(paymentId,payerId);
            if (payment.getState().equals("approved")){
                return "templates/paymentSuccess.html";
            }
        }catch (PayPalRESTException e){
            log.error("Error occurred:: ",e);
        }
        return "templates/paymentSuccess.html";
    }

    @GetMapping("payment/cancel")
    public String paymentCancel(){
        return "templates/paymentCancel.html";
    }

    @GetMapping("payment/error")
    public String paymentError(){
        return "templates/paymentError.html";
    }
}
