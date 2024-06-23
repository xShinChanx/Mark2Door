import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 800,
  duration: '5s', // Increased to 30 seconds
};

export default function () {
  const url = 'https://spring-security-service-jl4ebnk3lq-ez.a.run.app/auth/signin';
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

  sleep(1); // Adjust sleep time based on test needs
}
