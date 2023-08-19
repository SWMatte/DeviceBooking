package Device.deviceProject.service;


import Device.deviceProject.models.Subscription;

import java.util.List;

public interface iServiceClientLogistic {

 void sendEmail();

 void removeNotifyEmail(int idLogistic);
 void enableNotifyEmail(int idLogistic);


}
