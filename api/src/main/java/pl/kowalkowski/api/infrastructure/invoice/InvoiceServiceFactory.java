package pl.kowalkowski.api.infrastructure.invoice;


import pl.kowalkowski.api.facade.AttendanceFacade;

public class InvoiceServiceFactory {

    private InvoiceServiceFactory() {
    }

    public static InvoiceService createInvoiceService(InvoiceCalculator invoiceCalculator, AttendanceFacade attendanceFacade) {
        return new InvoiceServiceImpl(invoiceCalculator,attendanceFacade);
    }
}
