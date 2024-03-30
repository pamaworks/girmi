const MainRoutes = {
  path: '/main',
  meta: {
    requiresAuth: true
  },
  redirect: '/main/dashboard/default',
  component: () => import('@/layouts/full/FullLayout.vue'),
  children: [
    {
      name: 'LandingPage',
      path: '/',
      component: () => import('@/views/dashboards/default/DefaultDashboard.vue')
    },
    {
      name: 'Default',
      path: '/dashboard/default',
      component: () => import('@/views/dashboards/default/DefaultDashboard.vue')
    },
    {
      name: 'BoardList',
      path: '/contents/board/list',
      component: () => import('@/views/contents/board/BoardList.vue')
    },
    {
      name: 'BoardEdit',
      path: '/contents/board/:brdIdx',
      component: () => import('@/views/contents/board/BoardEdit.vue'),
      props: true
    },
    {
      name: 'ChatMain',
      path: '/contents/chat/main',
      component: () => import('@/views/contents/chat/ChatMain.vue')
    },
    {
      name: 'ChatRoom',
      path: '/contents/chat/room',
      component: () => import('@/views/contents/chat/ChatRoom.vue')
    },
    {
      name: 'Starter',
      path: '/starter',
      component: () => import('@/views/StarterPage.vue')
    },
    {
      name: 'Tabler Icons',
      path: '/icons/tabler',
      component: () => import('@/views/utilities/icons/TablerIcons.vue')
    },
    {
      name: 'Material Icons',
      path: '/icons/material',
      component: () => import('@/views/utilities/icons/MaterialIcons.vue')
    },
    {
      name: 'Typography',
      path: '/utils/typography',
      component: () => import('@/views/utilities/typography/TypographyPage.vue')
    },
    {
      name: 'Shadows',
      path: '/utils/shadows',
      component: () => import('@/views/utilities/shadows/ShadowPage.vue')
    },
    {
      name: 'Colors',
      path: '/utils/colors',
      component: () => import('@/views/utilities/colors/ColorPage.vue')
    }
  ]
};

export default MainRoutes;
