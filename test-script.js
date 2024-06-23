import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 100, // Number of virtual users
  duration: '30s', // Duration of the test
  // Adjust other options as needed
};

export default function () {
  const url = 'https://spring-security-service-jl4ebnk3lq-uc.a.run.app/auth/signin';
  const payload = JSON.stringify({
    email: 'anis@gmail.com',
    password: 'yeet',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let response = http.post(url, payload, params);

  check(response, {
    'status is 200': (r) => r.status === 200,
  });

  // Calculate the appropriate sleep time to achieve 100 req/sec
  // Since k6 executes iterations as quickly as possible, sleep is used to control the rate
  const desiredRps = 100; // Desired requests per second
  const sleepTime = 1 / desiredRps - response.timings.duration / 1000;
  
  if (sleepTime > 0) {
    sleep(sleepTime);
  }
}