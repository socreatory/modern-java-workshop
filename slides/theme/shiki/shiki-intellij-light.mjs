export const intellijLight = {
  name: 'intellij-islands-light',
  type: 'light',
  colors: {
    'editor.background': '#FFFFFF',
    'editor.foreground': '#080808',
  },
  settings: [
    { settings: { background: '#FFFFFF', foreground: '#080808' } },
    { scope: ['comment'], settings: { foreground: '#8C8C8C', fontStyle: 'italic' } },
    { scope: ['storage.type'], settings: { foreground: '#080808' } },
    {
      scope: ['keyword', 'storage.modifier', 'storage.type.primitive', 'constant.language', 'variable.language'],
      settings: { foreground: '#0033B3' },
    },
    { scope: ['keyword.operator'], settings: { foreground: '#080808' } },
    { scope: ['keyword.control.ternary'], settings: { foreground: '#080808' } },
    { scope: ['string', 'punctuation.definition.string'], settings: { foreground: '#067D17' } },
    { scope: ['constant.numeric'], settings: { foreground: '#1750EB' } },
  ],
};
