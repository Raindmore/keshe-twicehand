/**
 * Auth utilities
 */
export function getToken() { return localStorage.getItem('token') }
export function setToken(t) { localStorage.setItem('token', t) }
export function removeToken() { localStorage.removeItem('token') }
export function isLoggedIn() { return !!getToken() }
