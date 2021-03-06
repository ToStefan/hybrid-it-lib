package hybrid.it.internship.library.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import hybrid.it.internship.library.service.impl.RentServiceImpl;
import hybrid.it.internship.library.web.dto.PageDTO;
import hybrid.it.internship.library.web.dto.RentDTO;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class RentScheduler {

    private final RentServiceImpl rentService;
    private final ObjectMapper objectMapper;

    @Scheduled(cron = "${app.cronExpression}")
    public void rentOverdueScheduler() throws IOException {

        List<RentDTO> overdue = rentService.getOverdueRents(new PageDTO(1, 1));

        String filePath = System.getProperty("user.dir") + "/scheduler/overdue-rents-" + LocalDate.now() + ".txt";
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(objectMapper.writeValueAsBytes(overdue));
        fos.close();
    }
}