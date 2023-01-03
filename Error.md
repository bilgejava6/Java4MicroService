# HATA MESAJLARI LİSTESİ
    
    X   X   X   X
    |   |   |   |
    |   |   |   +---> mesajı
    |   |   +------>  alt işlem kodu
    |   +---------->  Servis adı
    +---------------> İşlem Kodu
    1 0 0 0 -> İşlem Başarılı
    2 0 0 0 -> İşlem Beklemede
    3 0 0 0 -> Yetkisiz İşlemler
    4 0 0 0 -> Hatalı İŞlemler
## Auth Service Hataları
    4100: "Parametreler eksik veya hatalı",
    4110: "Kullanıcı adı veya şifre hatalı",
    4111: "Girişlen Şifreler Uyuşmuyor",
    4112: "Kullanıcı adı zaten kayıtlı",
