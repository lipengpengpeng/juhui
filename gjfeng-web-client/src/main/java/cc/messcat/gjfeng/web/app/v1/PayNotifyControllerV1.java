package cc.messcat.gjfeng.web.app.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.messcat.gjfeng.common.web.BaseController;

@Controller
@RequestMapping(value="app/payNotify/",headers="app-version=v1.0")
public class PayNotifyControllerV1 extends BaseController {

}
