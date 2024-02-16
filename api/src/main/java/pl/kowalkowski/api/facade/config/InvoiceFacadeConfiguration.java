package pl.kowalkowski.api.facade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.facade.InvoiceFacade;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceService;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceServiceFactory;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceCalculator;

@Configuration
@RequiredArgsConstructor
public class InvoiceFacadeConfiguration {

    private final AttendanceFacade attendanceFacade;

    @Bean
    InvoiceFacade invoiceFacade() {
        InvoiceCalculator invoiceCalculator = new InvoiceCalculator();
        InvoiceService invoiceService = InvoiceServiceFactory.createInvoiceService(invoiceCalculator,attendanceFacade);
        return new InvoiceFacade(invoiceService);
    }

}
