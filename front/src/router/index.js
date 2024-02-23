import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import('../views/index.vue'),
      children: [
        {
          path: '/list',
          name: 'list',
          component: () => import('../views/list.vue')
        },
        {
          path: '/me',
          name: 'me',
          component: () => import('../views/me.vue')
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login.vue')
    }
  ]
})

export default router
