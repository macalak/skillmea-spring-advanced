# Cache technológie integrované v Spring-u

- Simple (ConcurrentMapCache) – defaul, v pamäti, vhodná pre testovanie.
  
- Ehcache – populárna Java cache, podpora TTL, persistencie.
  https://www.ehcache.org/
 
- Caffeine – moderná, rýchla cache s pokročilými možnosťami (TTL, LRU).
  https://github.com/ben-manes/caffeine/wiki
 
- Hazelcast / Infinispan – distribuované cache pre škálovateľné aplikácie.
  https://hazelcast.com/
 
- Redis – veľmi populárna cache pre distribuované systémy, podporuje TTL, persistenciu, pub/sub.
  https://redis.io/
 
- JCache/JSR-107 kompatibilné cache, čo je Java štandard pre cachovanie
  Čo znamená, že Spring rozumie JSR-107 anotáciám pre cachovanie.
  Viac: https://docs.spring.io/spring-framework/reference/integration/cache/jsr-107.html