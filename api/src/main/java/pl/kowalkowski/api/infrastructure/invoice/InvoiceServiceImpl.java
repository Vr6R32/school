package pl.kowalkowski.api.infrastructure.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceCalculator invoiceCalculator;
    private final AttendanceFacade attendanceFacade;


    @Override
    public InvoiceResponse<InvoiceParentDTO> getInvoiceForParentByIdAndPeriod(UUID parentId, Month month, int year) {
        List<AttendanceDTO> attendances = attendanceFacade.getAttendancesForParentByIdAndPeriod(parentId, month, year);
        InvoiceParentDTO invoiceParentDTO = invoiceCalculator.calculateParentSummary(attendances);

        log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE BY PARENT WITH ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]", parentId, year, month);
        return new InvoiceResponse<>("INVOICE GENERATED", HttpStatus.OK, invoiceParentDTO);
    }

    @Override
    public InvoiceResponse<InvoiceSchoolDTO> getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month month, int year) {
        List<AttendanceDTO> attendances = attendanceFacade.getAttendancesForSchoolByIdAndPeriod(schoolId, month, year);
        InvoiceSchoolDTO invoiceSchoolDTO = invoiceCalculator.calculateSchoolSummary(attendances);

        log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE BY SCHOOL WITH ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]", schoolId, year, month);
        return new InvoiceResponse<>("INVOICE GENERATED", HttpStatus.OK, invoiceSchoolDTO);
    }
}
