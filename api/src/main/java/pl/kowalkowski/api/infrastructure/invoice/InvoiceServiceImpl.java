package pl.kowalkowski.api.infrastructure.invoice;

import lombok.RequiredArgsConstructor;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceException;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceCalculator invoiceCalculator;
    private final AttendanceFacade attendanceFacade;



    @Override
    public InvoiceParentDTO getInvoiceForParentByIdAndPeriod(UUID parentId, Month integerMonth, int year) {
        List<AttendanceDTO> attendances = attendanceFacade.getAttendancesForParentByIdAndPeriod(parentId, integerMonth, year);
        if (attendances.isEmpty()) {
            throw new AttendanceException("ATTENDANCES LIST IS EMPTY");
        }
        return invoiceCalculator.calculateParentSummary(attendances);
    }

    @Override
    public InvoiceSchoolDTO getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month integerMonth, int year) {
        List<AttendanceDTO> attendances = attendanceFacade.getAttendancesForSchoolByIdAndPeriod(schoolId, integerMonth, year);
        if (attendances.isEmpty()) {
            throw new AttendanceException("ATTENDANCES LIST IS EMPTY");
        }
        return invoiceCalculator.calculateSchoolSummary(attendances);
    }
}
