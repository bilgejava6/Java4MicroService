# YAPILACAKLAR VE DİKKAT EDİLECEK HUSUSLAR

## Dikkat edilecekler
    1- Developer ortamı ile production ortamı configurasyonlar aynı olmamalıdır.
       bunu ayarlamak için farklı config serverler kullanmak mantılıdır.
    2- ConfigServer:
    docker build --build-arg JAR_FILE=config-server-git/build/libs/config-server-git-v.0.0.1.jar -t javaboost2/java4configgit:v001 .
    NOT: config server için hazırlanan clusterip adresini mutlaka servislere işleyiniz.
    NOT ÖNEMLİ!!! RabbitMQ için hazırladığımız kuyruğun oluşturulmadan önce 
    herhangi bir servis tarafından dinlenilmeye çalışılması servisin kapanmasına neden
    olmatadır. Bu nedenle Listen yaptığınız tüm servislerede RabbitConfig dosyasını 
    oluşturup dinleyeceğiniz kuyruğu açın.
    3- Auth Service:
    docker build --build-arg JAR_FILE=auth-microservice/build/libs/auth-microservice-v.0.0.1.jar -t javaboost2/java4auth:v001 .
    4- User Profile Service:
    docker build --build-arg JAR_FILE=userprofile-microservice/build/libs/userprofile-microservice-v.0.0.1.jar -t javaboost2/java4user:v001 .
    5- Api-Gateway Service:
    docker build --build-arg JAR_FILE=api-gateway-microservice/build/libs/api-gateway-microservice-v.0.0.1.jar -t javaboost2/java4gateway:v001 .