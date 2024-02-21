package pl.kowalkowski.api.infrastructure.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.*;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceCalculator invoiceCalculator;
    private final AttendanceFacade attendanceFacade;


    @Override
    public InvoiceResponse<InvoiceDTO> generateClientSummaryInvoice(UUID clientId, Month month, Year year, ClientType clientType) {
        switch (clientType) {
            case SCHOOL -> {
                List<AttendanceDTO> schoolAttendances = attendanceFacade.getClientAttendances(clientId, month, year, clientType);
                InvoiceSchoolDTO invoiceSchoolDTO = invoiceCalculator.calculateSchoolSummary(schoolAttendances, month, year);
                log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE BY SCHOOL WITH ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]", clientId, year, month);
                return new InvoiceResponse<>("INVOICE GENERATED", HttpStatus.OK, invoiceSchoolDTO);
            }
            case PARENT -> {
                List<AttendanceDTO> parentAttendances = attendanceFacade.getClientAttendances(clientId, month, year, clientType);
                InvoiceParentDTO invoiceParentDTO = invoiceCalculator.calculateParentSummary(parentAttendances, month, year);
                log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE BY PARENT WITH ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]", clientId, year, month);
                return new InvoiceResponse<>("INVOICE GENERATED", HttpStatus.OK, invoiceParentDTO);
            }
            default -> {
                log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE WITH UNSUPPORTED CLIENT TYPE [{}] FOR ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]", clientType, clientId, year, month);
                throw new InvoiceException("UNSUPPORTED CLIENT TYPE " + clientType);
            }
        }
    }
}
