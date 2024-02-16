package pl.kowalkowski.api.infrastructure.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
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
    public InvoiceParentDTO getInvoiceForParentByIdAndPeriod(UUID parentId, Month month, int year) {
        List<AttendanceDTO> attendances = attendanceFacade.getAttendancesForParentByIdAndPeriod(parentId, month, year);
        log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE BY PARENT WITH ID {} FOR PERIOD -> YEAR: {} MONTH: {}",parentId,year,month);
        return invoiceCalculator.calculateParentSummary(attendances);
    }

    @Override
    public InvoiceSchoolDTO getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month month, int year) {
        List<AttendanceDTO> attendances = attendanceFacade.getAttendancesForSchoolByIdAndPeriod(schoolId, month, year);
        log.info("[INVOICE-SERVICE] -> REQUESTED INVOICE BY SCHOOL WITH ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]",schoolId,year,month);
        return invoiceCalculator.calculateSchoolSummary(attendances);
    }
}
