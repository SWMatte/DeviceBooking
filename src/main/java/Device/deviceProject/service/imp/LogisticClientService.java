package Device.deviceProject.service.imp;

import Device.deviceProject.models.*;
import Device.deviceProject.repositories.ClientSubRepository;
import Device.deviceProject.repositories.SubscriptionRepository;
import Device.deviceProject.repositories.DeviceRepository;
import Device.deviceProject.repositories.LogisticClientRepository;
import Device.deviceProject.service.iService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogisticClientService implements iService<LogisticClient> {
    @Autowired
    LogisticClientRepository logisticClientRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ClientSubRepository clientSubRepository;

    @Override
    public List<LogisticClient> findAll() {
        return logisticClientRepository.findAll();
    }

    @Override
    public void add(LogisticClient element) throws Exception {
        LogisticClient aziendaEsistente = logisticClientRepository.findByCfLogistic(element.getCfLogistic());

        if (aziendaEsistente != null) {
            List<Subscription> listaAbbonamenti = element.getListSubscription().stream().map(x -> subscriptionRepository.findById(x.getIdSubscription()).orElseThrow()).collect(Collectors.toList());

            aziendaEsistente.getListSubscrition().addAll(listaAbbonamenti);
            logisticClientRepository.save(aziendaEsistente);

        } else {
            List<Subscription> listaAbbonamenti = element.getListSubscription().stream().map(x -> subscriptionRepository.findById(x.getIdSubscription()).orElseThrow()).collect(Collectors.toList());
            element.setListSubscription(listaAbbonamenti);
            logisticClientRepository.save(element);
        }

    }





    @Override
    public void remove(int id) throws Exception {
        logisticClientRepository.deleteById(id);
    }

    @Override
    public void update(LogisticClient element) throws Exception {

    }


}
